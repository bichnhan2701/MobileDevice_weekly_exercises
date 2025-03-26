package com.example.myappfirebase.ui.screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myappfirebase.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GoogleSignInScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val oneTapClient: SignInClient = remember { Identity.getSignInClient(context) }

    var userName by remember { mutableStateOf<String?>(null) }
    var userEmail by remember { mutableStateOf<String?>(null) }

    val signInRequest = BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            try {
                val credential: SignInCredential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                if (idToken != null) {
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener { authResult ->
                            if (authResult.isSuccessful) {
                                val user = auth.currentUser
                                userName = user?.displayName ?: "Người dùng"
                                userEmail = user?.email ?: "Không có email"

                                // Encode để tránh lỗi khi truyền dữ liệu
                                val encodedName = URLEncoder.encode(userName, StandardCharsets.UTF_8.toString())
                                val encodedEmail = URLEncoder.encode(userEmail, StandardCharsets.UTF_8.toString())

                                navController.navigate("home/$encodedName/$encodedEmail") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(context, "Lỗi đăng nhập: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Đăng nhập", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener { result ->
                    launcher.launch(IntentSenderRequest.Builder(result.pendingIntent).build())
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Không thể đăng nhập: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
        }) {
            Text(text = "Đăng nhập bằng Google")
        }
    }
}
