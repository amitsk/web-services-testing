
package com.github.webservicetesting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class SearchResult {

    private String id;
    private String xmlns;
    private String Title;
    private String Address;
    private String City;
    private String State;
    private String Phone;
    private String Latitude;
    private String Longitude;
    private Rating Rating;
    private String Distance;
    private String Url;
    private String ClickUrl;
    private String MapUrl;
    private String BusinessUrl;
    private String BusinessClickUrl;
    private Categories Categories;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The xmlns
     */
    public String getXmlns() {
        return xmlns;
    }

    /**
     * @param xmlns The xmlns
     */
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    /**
     * @return The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return The City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City The City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return The State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State The State
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return The Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone The Phone
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * @return The Latitude
     */
    public String getLatitude() {
        return Latitude;
    }

    /**
     * @param Latitude The Latitude
     */
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * @return The Longitude
     */
    public String getLongitude() {
        return Longitude;
    }

    /**
     * @param Longitude The Longitude
     */
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * @return The Rating
     */
    public Rating getRating() {
        return Rating;
    }

    /**
     * @param Rating The Rating
     */
    public void setRating(Rating Rating) {
        this.Rating = Rating;
    }

    /**
     * @return The Distance
     */
    public String getDistance() {
        return Distance;
    }

    /**
     * @param Distance The Distance
     */
    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    /**
     * @return The Url
     */
    public String getUrl() {
        return Url;
    }

    /**
     * @param Url The Url
     */
    public void setUrl(String Url) {
        this.Url = Url;
    }

    /**
     * @return The ClickUrl
     */
    public String getClickUrl() {
        return ClickUrl;
    }

    /**
     * @param ClickUrl The ClickUrl
     */
    public void setClickUrl(String ClickUrl) {
        this.ClickUrl = ClickUrl;
    }

    /**
     * @return The MapUrl
     */
    public String getMapUrl() {
        return MapUrl;
    }

    /**
     * @param MapUrl The MapUrl
     */
    public void setMapUrl(String MapUrl) {
        this.MapUrl = MapUrl;
    }

    /**
     * @return The BusinessUrl
     */
    public String getBusinessUrl() {
        return BusinessUrl;
    }

    /**
     * @param BusinessUrl The BusinessUrl
     */
    public void setBusinessUrl(String BusinessUrl) {
        this.BusinessUrl = BusinessUrl;
    }

    /**
     * @return The BusinessClickUrl
     */
    public String getBusinessClickUrl() {
        return BusinessClickUrl;
    }

    /**
     * @param BusinessClickUrl The BusinessClickUrl
     */
    public void setBusinessClickUrl(String BusinessClickUrl) {
        this.BusinessClickUrl = BusinessClickUrl;
    }

    /**
     * @return The Categories
     */
    public Categories getCategories() {
        return Categories;
    }

    /**
     * @param Categories The Categories
     */
    public void setCategories(
            Categories Categories) {
        this.Categories = Categories;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Generated("org.jsonschema2pojo")
    public static class Categories {

        private List<Object> Category = new ArrayList<Object>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The Category
         */
        public List<Object> getCategory() {
            return Category;
        }

        /**
         * @param Category The Category
         */
        public void setCategory(List<Object> Category) {
            this.Category = Category;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @Generated("org.jsonschema2pojo")
    public static class Rating {

        private String AverageRating;
        private String TotalRatings;
        private String TotalReviews;
        private Object LastReviewDate;
        private Object LastReviewIntro;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The AverageRating
         */
        public String getAverageRating() {
            return AverageRating;
        }

        /**
         * @param AverageRating The AverageRating
         */
        public void setAverageRating(String AverageRating) {
            this.AverageRating = AverageRating;
        }

        /**
         * @return The TotalRatings
         */
        public String getTotalRatings() {
            return TotalRatings;
        }

        /**
         * @param TotalRatings The TotalRatings
         */
        public void setTotalRatings(String TotalRatings) {
            this.TotalRatings = TotalRatings;
        }

        /**
         * @return The TotalReviews
         */
        public String getTotalReviews() {
            return TotalReviews;
        }

        /**
         * @param TotalReviews The TotalReviews
         */
        public void setTotalReviews(String TotalReviews) {
            this.TotalReviews = TotalReviews;
        }

        /**
         * @return The LastReviewDate
         */
        public Object getLastReviewDate() {
            return LastReviewDate;
        }

        /**
         * @param LastReviewDate The LastReviewDate
         */
        public void setLastReviewDate(Object LastReviewDate) {
            this.LastReviewDate = LastReviewDate;
        }

        /**
         * @return The LastReviewIntro
         */
        public Object getLastReviewIntro() {
            return LastReviewIntro;
        }

        /**
         * @param LastReviewIntro The LastReviewIntro
         */
        public void setLastReviewIntro(Object LastReviewIntro) {
            this.LastReviewIntro = LastReviewIntro;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}