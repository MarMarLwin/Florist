package com.example.floristbypo.models

 class Owner {
    val reputation = 0
    val user_id: Long = 0
    val user_type: String? = null
    val profile_image: String? = null
    val display_name: String? = null
    val link: String? = null
}

 class Item {
    val owner: Owner? = null
    val is_accepted = false
    val score = 0
    val last_activity_date: Long = 0
    val creation_date: Long = 0
    val answer_id: Long = 0
    val question_id: Long = 0
}

class StackApiResponse {
    val items:List<Item>? = null
    val has_more = false
    val quota_max = 0
    val quota_remaining = 0
}