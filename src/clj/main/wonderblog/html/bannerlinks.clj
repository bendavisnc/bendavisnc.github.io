(ns wonderblog.html.bannerlinks
  (:require
    [wonderblog.constants :as constants]
    [wonderblog.data.bannerlinks :as bannerlinks-data]
  ))

(defn banner-link [datum]
  [:a {:href (:href datum)} (:title datum)])

(defn html []
  `[:div 
    {:class "bannerlinks"}
    ~@
    (reduce
      (fn [acc, d]
        (conj acc (banner-link d)))
      []
      bannerlinks-data/data)])
