package com.github.amitsk.webservicetesting


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class QueryResult {
  var query: Query? = null

  class Query {
    var count: Int? = null
    var lang: String? = null
    var created: String? = null
    var results: Results? = null

    class Results {
      @JsonProperty("Result")
      var result: List<SearchResult>? = null
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class SearchResult {

      @JsonProperty("Title")
      var title: String? = null

      @JsonProperty("Rating")
      var rating: Rating? = null

      @JsonIgnoreProperties(ignoreUnknown = true)
      class Rating {
        @JsonProperty("TotalReviews")
        var totalReviews: String? = null

      }
    }
  }
}