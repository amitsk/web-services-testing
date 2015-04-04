package com.github.webservicetesting

import com.fasterxml.jackson.annotation.*

import javax.annotation.Generated

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class GovTrackRole {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("objects")
    private List<com.github.webservicetesting.GovTrackRole.Object> objects = new ArrayList<com.github.webservicetesting.GovTrackRole.Object>();
    @JsonIgnore
    private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("objects")
    public List<com.github.webservicetesting.GovTrackRole.Object> getObjects() {
        return objects;
    }

    @JsonProperty("objects")
    public void setObjects(List<com.github.webservicetesting.GovTrackRole.Object> objects) {
        this.objects = objects;
    }

    @JsonAnyGetter
    public Map<String, java.lang.Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, java.lang.Object value) {
        this.additionalProperties.put(name, value);
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Generated("org.jsonschema2pojo")
    public static class Meta {

        @JsonProperty("limit")
        private Integer limit;
        @JsonProperty("offset")
        private Integer offset;
        @JsonProperty("total_count")
        private Integer totalCount;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         *
         * @return
         * The limit
         */
        @JsonProperty("limit")
        public Integer getLimit() {
            return limit;
        }

        /**
         *
         * @param limit
         * The limit
         */
        @JsonProperty("limit")
        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        /**
         *
         * @return
         * The offset
         */
        @JsonProperty("offset")
        public Integer getOffset() {
            return offset;
        }

        /**
         *
         * @param offset
         * The offset
         */
        @JsonProperty("offset")
        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        /**
         *
         * @return
         * The totalCount
         */
        @JsonProperty("total_count")
        public Integer getTotalCount() {
            return totalCount;
        }

        /**
         *
         * @param totalCount
         * The total_count
         */
        @JsonProperty("total_count")
        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    public static class Object {

        @JsonProperty("caucus")
        private java.lang.Object caucus;
        @JsonProperty("congress_numbers")
        private List<Integer> congressNumbers = new ArrayList<Integer>();
        @JsonProperty("current")
        private Boolean current;
        @JsonProperty("description")
        private String description;
        @JsonProperty("district")
        private Integer district;
        @JsonProperty("enddate")
        private String enddate;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("leadership_title")
        private java.lang.Object leadershipTitle;
        @JsonProperty("party")
        private String party;
        @JsonProperty("person")
        private Person person;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("role_type")
        private String roleType;
        @JsonProperty("role_type_label")
        private String roleTypeLabel;
        @JsonProperty("senator_class")
        private java.lang.Object senatorClass;
        @JsonProperty("senator_class_label")
        private String senatorClassLabel;
        @JsonProperty("senator_rank")
        private java.lang.Object senatorRank;
        @JsonProperty("senator_rank_label")
        private String senatorRankLabel;
        @JsonProperty("startdate")
        private String startdate;
        @JsonProperty("state")
        private String state;
        @JsonProperty("title")
        private String title;
        @JsonProperty("title_long")
        private String titleLong;
        @JsonProperty("website")
        private String website;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         *
         * @return
         * The caucus
         */
        @JsonProperty("caucus")
        public java.lang.Object getCaucus() {
            return caucus;
        }

        /**
         *
         * @param caucus
         * The caucus
         */
        @JsonProperty("caucus")
        public void setCaucus(java.lang.Object caucus) {
            this.caucus = caucus;
        }

        /**
         *
         * @return
         * The congressNumbers
         */
        @JsonProperty("congress_numbers")
        public List<Integer> getCongressNumbers() {
            return congressNumbers;
        }

        /**
         *
         * @param congressNumbers
         * The congress_numbers
         */
        @JsonProperty("congress_numbers")
        public void setCongressNumbers(List<Integer> congressNumbers) {
            this.congressNumbers = congressNumbers;
        }

        /**
         *
         * @return
         * The current
         */
        @JsonProperty("current")
        public Boolean getCurrent() {
            return current;
        }

        /**
         *
         * @param current
         * The current
         */
        @JsonProperty("current")
        public void setCurrent(Boolean current) {
            this.current = current;
        }

        /**
         *
         * @return
         * The description
         */
        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         * The description
         */
        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         * The district
         */
        @JsonProperty("district")
        public Integer getDistrict() {
            return district;
        }

        /**
         *
         * @param district
         * The district
         */
        @JsonProperty("district")
        public void setDistrict(Integer district) {
            this.district = district;
        }

        /**
         *
         * @return
         * The enddate
         */
        @JsonProperty("enddate")
        public String getEnddate() {
            return enddate;
        }

        /**
         *
         * @param enddate
         * The enddate
         */
        @JsonProperty("enddate")
        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        /**
         *
         * @return
         * The id
         */
        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The leadershipTitle
         */
        @JsonProperty("leadership_title")
        public java.lang.Object getLeadershipTitle() {
            return leadershipTitle;
        }

        /**
         *
         * @param leadershipTitle
         * The leadership_title
         */
        @JsonProperty("leadership_title")
        public void setLeadershipTitle(java.lang.Object leadershipTitle) {
            this.leadershipTitle = leadershipTitle;
        }

        /**
         *
         * @return
         * The party
         */
        @JsonProperty("party")
        public String getParty() {
            return party;
        }

        /**
         *
         * @param party
         * The party
         */
        @JsonProperty("party")
        public void setParty(String party) {
            this.party = party;
        }

        /**
         *
         * @return
         * The person
         */
        @JsonProperty("person")
        public Person getPerson() {
            return person;
        }

        /**
         *
         * @param person
         * The person
         */
        @JsonProperty("person")
        public void setPerson(Person person) {
            this.person = person;
        }

        /**
         *
         * @return
         * The phone
         */
        @JsonProperty("phone")
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        @JsonProperty("phone")
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The roleType
         */
        @JsonProperty("role_type")
        public String getRoleType() {
            return roleType;
        }

        /**
         *
         * @param roleType
         * The role_type
         */
        @JsonProperty("role_type")
        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }

        /**
         *
         * @return
         * The roleTypeLabel
         */
        @JsonProperty("role_type_label")
        public String getRoleTypeLabel() {
            return roleTypeLabel;
        }

        /**
         *
         * @param roleTypeLabel
         * The role_type_label
         */
        @JsonProperty("role_type_label")
        public void setRoleTypeLabel(String roleTypeLabel) {
            this.roleTypeLabel = roleTypeLabel;
        }

        /**
         *
         * @return
         * The senatorClass
         */
        @JsonProperty("senator_class")
        public java.lang.Object getSenatorClass() {
            return senatorClass;
        }

        /**
         *
         * @param senatorClass
         * The senator_class
         */
        @JsonProperty("senator_class")
        public void setSenatorClass(java.lang.Object senatorClass) {
            this.senatorClass = senatorClass;
        }

        /**
         *
         * @return
         * The senatorClassLabel
         */
        @JsonProperty("senator_class_label")
        public String getSenatorClassLabel() {
            return senatorClassLabel;
        }

        /**
         *
         * @param senatorClassLabel
         * The senator_class_label
         */
        @JsonProperty("senator_class_label")
        public void setSenatorClassLabel(String senatorClassLabel) {
            this.senatorClassLabel = senatorClassLabel;
        }

        /**
         *
         * @return
         * The senatorRank
         */
        @JsonProperty("senator_rank")
        public java.lang.Object getSenatorRank() {
            return senatorRank;
        }

        /**
         *
         * @param senatorRank
         * The senator_rank
         */
        @JsonProperty("senator_rank")
        public void setSenatorRank(java.lang.Object senatorRank) {
            this.senatorRank = senatorRank;
        }

        /**
         *
         * @return
         * The senatorRankLabel
         */
        @JsonProperty("senator_rank_label")
        public String getSenatorRankLabel() {
            return senatorRankLabel;
        }

        /**
         *
         * @param senatorRankLabel
         * The senator_rank_label
         */
        @JsonProperty("senator_rank_label")
        public void setSenatorRankLabel(String senatorRankLabel) {
            this.senatorRankLabel = senatorRankLabel;
        }

        /**
         *
         * @return
         * The startdate
         */
        @JsonProperty("startdate")
        public String getStartdate() {
            return startdate;
        }

        /**
         *
         * @param startdate
         * The startdate
         */
        @JsonProperty("startdate")
        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        /**
         *
         * @return
         * The state
         */
        @JsonProperty("state")
        public String getState() {
            return state;
        }

        /**
         *
         * @param state
         * The state
         */
        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        /**
         *
         * @return
         * The title
         */
        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The titleLong
         */
        @JsonProperty("title_long")
        public String getTitleLong() {
            return titleLong;
        }

        /**
         *
         * @param titleLong
         * The title_long
         */
        @JsonProperty("title_long")
        public void setTitleLong(String titleLong) {
            this.titleLong = titleLong;
        }

        /**
         *
         * @return
         * The website
         */
        @JsonProperty("website")
        public String getWebsite() {
            return website;
        }

        /**
         *
         * @param website
         * The website
         */
        @JsonProperty("website")
        public void setWebsite(String website) {
            this.website = website;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Generated("org.jsonschema2pojo")

    public static class Person {

        @JsonProperty("bioguideid")
        private String bioguideid;
        @JsonProperty("birthday")
        private String birthday;
        @JsonProperty("cspanid")
        private Integer cspanid;
        @JsonProperty("firstname")
        private String firstname;
        @JsonProperty("gender")
        private String gender;
        @JsonProperty("gender_label")
        private String genderLabel;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("lastname")
        private String lastname;
        @JsonProperty("link")
        private String link;
        @JsonProperty("middlename")
        private String middlename;
        @JsonProperty("name")
        private String name;
        @JsonProperty("namemod")
        private String namemod;
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("osid")
        private String osid;
        @JsonProperty("pvsid")
        private String pvsid;
        @JsonProperty("sortname")
        private String sortname;
        @JsonProperty("twitterid")
        private String twitterid;
        @JsonProperty("youtubeid")
        private String youtubeid;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         *
         * @return
         * The bioguideid
         */
        @JsonProperty("bioguideid")
        public String getBioguideid() {
            return bioguideid;
        }

        /**
         *
         * @param bioguideid
         * The bioguideid
         */
        @JsonProperty("bioguideid")
        public void setBioguideid(String bioguideid) {
            this.bioguideid = bioguideid;
        }

        /**
         *
         * @return
         * The birthday
         */
        @JsonProperty("birthday")
        public String getBirthday() {
            return birthday;
        }

        /**
         *
         * @param birthday
         * The birthday
         */
        @JsonProperty("birthday")
        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        /**
         *
         * @return
         * The cspanid
         */
        @JsonProperty("cspanid")
        public Integer getCspanid() {
            return cspanid;
        }

        /**
         *
         * @param cspanid
         * The cspanid
         */
        @JsonProperty("cspanid")
        public void setCspanid(Integer cspanid) {
            this.cspanid = cspanid;
        }

        /**
         *
         * @return
         * The firstname
         */
        @JsonProperty("firstname")
        public String getFirstname() {
            return firstname;
        }

        /**
         *
         * @param firstname
         * The firstname
         */
        @JsonProperty("firstname")
        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        /**
         *
         * @return
         * The gender
         */
        @JsonProperty("gender")
        public String getGender() {
            return gender;
        }

        /**
         *
         * @param gender
         * The gender
         */
        @JsonProperty("gender")
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         *
         * @return
         * The genderLabel
         */
        @JsonProperty("gender_label")
        public String getGenderLabel() {
            return genderLabel;
        }

        /**
         *
         * @param genderLabel
         * The gender_label
         */
        @JsonProperty("gender_label")
        public void setGenderLabel(String genderLabel) {
            this.genderLabel = genderLabel;
        }

        /**
         *
         * @return
         * The id
         */
        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The lastname
         */
        @JsonProperty("lastname")
        public String getLastname() {
            return lastname;
        }

        /**
         *
         * @param lastname
         * The lastname
         */
        @JsonProperty("lastname")
        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        /**
         *
         * @return
         * The link
         */
        @JsonProperty("link")
        public String getLink() {
            return link;
        }

        /**
         *
         * @param link
         * The link
         */
        @JsonProperty("link")
        public void setLink(String link) {
            this.link = link;
        }

        /**
         *
         * @return
         * The middlename
         */
        @JsonProperty("middlename")
        public String getMiddlename() {
            return middlename;
        }

        /**
         *
         * @param middlename
         * The middlename
         */
        @JsonProperty("middlename")
        public void setMiddlename(String middlename) {
            this.middlename = middlename;
        }

        /**
         *
         * @return
         * The name
         */
        @JsonProperty("name")
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The namemod
         */
        @JsonProperty("namemod")
        public String getNamemod() {
            return namemod;
        }

        /**
         *
         * @param namemod
         * The namemod
         */
        @JsonProperty("namemod")
        public void setNamemod(String namemod) {
            this.namemod = namemod;
        }

        /**
         *
         * @return
         * The nickname
         */
        @JsonProperty("nickname")
        public String getNickname() {
            return nickname;
        }

        /**
         *
         * @param nickname
         * The nickname
         */
        @JsonProperty("nickname")
        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        /**
         *
         * @return
         * The osid
         */
        @JsonProperty("osid")
        public String getOsid() {
            return osid;
        }

        /**
         *
         * @param osid
         * The osid
         */
        @JsonProperty("osid")
        public void setOsid(String osid) {
            this.osid = osid;
        }

        /**
         *
         * @return
         * The pvsid
         */
        @JsonProperty("pvsid")
        public String getPvsid() {
            return pvsid;
        }

        /**
         *
         * @param pvsid
         * The pvsid
         */
        @JsonProperty("pvsid")
        public void setPvsid(String pvsid) {
            this.pvsid = pvsid;
        }

        /**
         *
         * @return
         * The sortname
         */
        @JsonProperty("sortname")
        public String getSortname() {
            return sortname;
        }

        /**
         *
         * @param sortname
         * The sortname
         */
        @JsonProperty("sortname")
        public void setSortname(String sortname) {
            this.sortname = sortname;
        }

        /**
         *
         * @return
         * The twitterid
         */
        @JsonProperty("twitterid")
        public String getTwitterid() {
            return twitterid;
        }

        /**
         *
         * @param twitterid
         * The twitterid
         */
        @JsonProperty("twitterid")
        public void setTwitterid(String twitterid) {
            this.twitterid = twitterid;
        }

        /**
         *
         * @return
         * The youtubeid
         */
        @JsonProperty("youtubeid")
        public String getYoutubeid() {
            return youtubeid;
        }

        /**
         *
         * @param youtubeid
         * The youtubeid
         */
        @JsonProperty("youtubeid")
        public void setYoutubeid(String youtubeid) {
            this.youtubeid = youtubeid;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}