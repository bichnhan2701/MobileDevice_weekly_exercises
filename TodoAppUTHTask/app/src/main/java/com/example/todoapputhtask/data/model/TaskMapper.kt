package com.example.todoapputhtask.data.model

import com.example.todoapputhtask.data.local.LocalTaskEntity

fun LocalTaskEntity.toTask(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        status = this.status,
        priority = this.priority,
        category = this.category,
        dueDate = this.dueDate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        subtasks = emptyList(),
        attachments = emptyList(),
        reminders = emptyList()
    )
}