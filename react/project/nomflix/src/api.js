import axios from "axios";

const api = axios.create({
    baseURL: "https://api.themoviedb.org/3/",
    params: {
        "api_key": "b27ea2f2fe32f9e3b08cbdb7951f353a",
        language: "en-US"
    }
});

export const moviesApi = {
    nowPlaying: () => api.get("movie/now_playing"),
    upcoming: () => api.get("movie/upcoming"),
    popular: () => api.get("movie/popular"),
    movieDetail: (id) => 
        api.get(`movie/${id}`, {
            params: {
                append_to_response: "videos"
            }
        }),
    search: (term) => 
        api.get("search/movie", {
            params: {
                query: encodeURIComponent(term)
            }
        })
};

export const tvApi = {
    topRated: () => api.get("tv/top_rated"),
    popular: () => api.get("tv/popular"),
    airingToday: () => api.get("tv/airing_today"),
    tvDetail: (id) => 
        api.get(`tv/${id}`, {
            parmas: {
                append_to_response: "videos"
            }
        }),
    search: (term) => 
        api.get("search/tv", {
            params: {
                query: encodeURIComponent(term)
            }
        })
};