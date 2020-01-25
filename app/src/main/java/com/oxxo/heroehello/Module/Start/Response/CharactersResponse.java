package com.oxxo.heroehello.Module.Start.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CharactersResponse {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("attributionText")
    @Expose
    private String attributionText;

    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;

    @SerializedName("etag")
    @Expose
    private String etag;

    @SerializedName("data")
    @Expose
    private Data data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return this.attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return this.attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Thumbnail {

        @SerializedName("path")
        @Expose
        private String path;

        @SerializedName("extension")
        @Expose
        private String extension;

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getExtension() {
            return this.extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

    }

    public class Items {

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;

        @SerializedName("name")
        @Expose
        private String name;

        public String getResourceURI() {
            return this.resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Comics {

        @SerializedName("available")
        @Expose
        private int available;

        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;

        @SerializedName("items")
        @Expose
        private List<Items> items;

        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return this.available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return this.collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<Items> getItems() {
            return this.items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public int getReturned() {
            return this.returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

    }

    public class Series {

        @SerializedName("available")
        @Expose
        private int available;

        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;

        @SerializedName("items")
        @Expose
        private List<SerieItems> items;

        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return this.available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return this.collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<SerieItems> getItems() {
            return this.items;
        }

        public void setItems(List<SerieItems> items) {
            this.items = items;
        }

        public int getReturned() {
            return this.returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

    }

    public class StorieItems {

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("type")
        @Expose
        private String type;

        public String getResourceURI() {
            return this.resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public class Stories {

        @SerializedName("available")
        @Expose
        private int available;

        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;

        @SerializedName("items")
        @Expose
        private List<StorieItems> items;

        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return this.available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return this.collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<StorieItems> getItems() {
            return this.items;
        }

        public void setItems(List<StorieItems> items) {
            this.items = items;
        }

        public int getReturned() {
            return this.returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

    }

    public class SerieItems {

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;

        @SerializedName("name")
        @Expose
        private String name;

        public String getResourceURI() {
            return this.resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Events {

        @SerializedName("available")
        @Expose
        private int available;

        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;

        @SerializedName("items")
        @Expose
        private List<Items> items;

        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return this.available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return this.collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<Items> getItems() {
            return this.items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public int getReturned() {
            return this.returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }

    }

    public class Urls {

        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("url")
        @Expose
        private String url;

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Results {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("modified")
        @Expose
        private String modified;

        @SerializedName("thumbnail")
        @Expose
        private Thumbnail thumbnail;

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;

        @SerializedName("comics")
        @Expose
        private Comics comics;

        @SerializedName("series")
        @Expose
        private Series series;

        @SerializedName("stories")
        @Expose
        private Stories stories;

        @SerializedName("events")
        @Expose
        private Events events;

        @SerializedName("urls")
        @Expose
        private List<Urls> urls;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModified() {
            return this.modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Thumbnail getThumbnail() {
            return this.thumbnail;
        }

        public void setThumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getResourceURI() {
            return this.resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public Comics getComics() {
            return this.comics;
        }

        public void setComics(Comics comics) {
            this.comics = comics;
        }

        public Series getSeries() {
            return this.series;
        }

        public void setSeries(Series series) {
            this.series = series;
        }

        public Stories getStories() {
            return this.stories;
        }

        public void setStories(Stories stories) {
            this.stories = stories;
        }

        public Events getEvents() {
            return this.events;
        }

        public void setEvents(Events events) {
            this.events = events;
        }

        public List<Urls> getUrls() {
            return this.urls;
        }

        public void setUrls(List<Urls> urls) {
            this.urls = urls;
        }

    }

    public class Data {

        @SerializedName("offset")
        @Expose
        private int offset;

        @SerializedName("limit")
        @Expose
        private int limit;

        @SerializedName("total")
        @Expose
        private int total;

        @SerializedName("count")
        @Expose
        private int count;

        @SerializedName("results")
        @Expose
        private List<Results> results;

        public int getOffset() {
            return this.offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return this.limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return this.total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Results> getResults() {
            return this.results;
        }

        public void setResults(List<Results> results) {
            this.results = results;
        }

    }

}