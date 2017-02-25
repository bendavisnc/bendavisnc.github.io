(ns wonderblog.html.bannerlinks
  (:require
    [wonderblog.constants :as constants]
    [wonderblog.data.bannerlinks :as bannerlinks-data]
  ))

(defn banner-link [datum]
  [:a 
   {
     :class "navlink"
     :href (:href datum)
     :style (str 
              "background-image: url(\"" (:img datum) "\"); "
              ; "background-size: 10vh 10vh; "
              "background-size: contain;"
              "background-repeat: no-repeat;")
   } 
  ])

(defn html []
  `[:div 
    {:class "bannerlinks"}
    ~@
    (reduce
      (fn [acc, d]
        (conj acc (banner-link d)))
      []
      bannerlinks-data/data)])
