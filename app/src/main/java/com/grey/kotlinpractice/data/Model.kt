package com.grey.kotlinpractice.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



object Model {

    data class ResultCount(val resultCount: Int)
    data class Results(val results: ArrayList<Podcast>)

    data class Podcast(val artistName: String, val feedUrl: String) {

        @SerializedName("artworkUrl600")
        @Expose
        public val artworkUrl600: String? = null

        @SerializedName("trackCount")
        @Expose
        public val trackCount: Int? = null

        @SerializedName("collectionName")
        @Expose
        public val collectionName: String? = null

    }

    data class Ep(val title: String, val description: String, val url: String, val length: String, val pubDate: String ){}
}