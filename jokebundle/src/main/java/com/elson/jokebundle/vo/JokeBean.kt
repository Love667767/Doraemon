package com.elson.jokebundle.vo

/**
 * Created by apple on 2018/2/28.
 */

data class Response<T>(
        val message: String,
        val data: T
)

data class JokeList(
        val has_more: Boolean,
        val tip: String,
        val has_new_message: String,
        val max_time: Double,
        val min_time: Double,
        val data: List<JokeGroup>
)


data class JokeGroup(val group: Joke)

data class Joke(
        val text: String,
        val neihan_hot_start_time: String

)


