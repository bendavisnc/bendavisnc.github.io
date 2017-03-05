(ns wonderblog.html.bannerlinks
  (:require
    [wonderblog.constants :as constants]
    [wonderblog.data.bannerlinks :as bannerlinks-data]
  ))

(defn banner-link [datum, index-val]
  [:a 
   {
     :class (str "navlink " "navlink-" index-val)
     :href (:href datum)
     :style (str 
              "background-image: url(\"" (:img datum) "\"); "
            )
              ; "background-size: 10vh 10vh; "
              ; "left: " left-pos "px;"
              ; "background-size: contain;"
              ; "background-repeat: no-repeat;")
   } 
  ])

(defn html []
  (map
    banner-link
    bannerlinks-data/data
    (list 0 1 2)
    ))
