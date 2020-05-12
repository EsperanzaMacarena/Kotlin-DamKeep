package com.salesianostrianafct.damkeep.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Note(
        @NotEmpty
        var title: String,
        var body: String,

        @JsonBackReference
        @ManyToOne
        var author: MyUser? = null,

        @CreatedDate
        var createdOn: LocalDateTime?=null,

        @LastModifiedDate
        var editedOn: LocalDateTime?=null,

        @Id @GeneratedValue
        val id: UUID? = null

)