(ns wonderblog.html.bannerlinks
  (:require
    [wonderblog.constants :as constants]
    [wonderblog.data.bannerlinks :as bannerlinks-data]
  ))

(def spacing-val 150)

(def left-pos-vals
  (map
    #(* % spacing-val)
    (range 0 3)))

(defn banner-link [datum, left-pos]
  [:a 
   {
     :class "navlink"
     :href (:href datum)
     :style (str 
              "background-image: url(\"" (:img datum) "\"); "
              ; "background-size: 10vh 10vh; "
              "left: " left-pos "px;"
              "background-size: contain;"
              "background-repeat: no-repeat;")
   } 
  ])

(defn html []
  [:div 
    {:class "bannerlinks"}
    (map
      banner-link
      bannerlinks-data/data
      ; (list 0 20 40)
      left-pos-vals
      )
  ])
