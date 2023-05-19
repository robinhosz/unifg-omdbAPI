package io.github.robinhosz.unifg.cache;

import java.util.HashMap;
import java.util.Map;

public class MovieCache {

    private Map<String, String> cache;

    public MovieCache() {
        this.cache = new HashMap<>();
    }

    public void addToCache(String movieTitle, String response) {
        cache.put(movieTitle, response);
    }

    public String getFromCache(String movieTitle) {
        for (String cachedTitle : cache.keySet()) {
            if (cachedTitle.contains(movieTitle)) {
                return cache.get(cachedTitle);
            }
        }
        return null;
    }

    public boolean isInCache(String movieTitle) {
        String lowercaseMovieTitle = movieTitle.toLowerCase();
        for (String cachedTitle : cache.keySet()) {
            if (cachedTitle.toLowerCase().contains(lowercaseMovieTitle)) {
                return true;
            }
        }
        return false;
    }
}
