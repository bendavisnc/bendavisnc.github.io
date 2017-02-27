(ns wonderblog.html.links
  (:require 
    [wonderblog.data.links.core :as links-data]
    [wonderblog.data.links.personal :as links-personal-data]
    [wonderblog.data.links.books :as links-books-data]
    [wonderblog.data.links.talks :as links-talks-data]
    [wonderblog.html.core :refer [render-full]]
  ))


(defn personal-link [datum]
  [:a 
    {
     :href (:href datum)
     :target "_blank"
    } 
    (:title datum)])

(def personal-content
  (list
    [:div 
      {:class "content-title"}
      "personal"]
    [:div 
      {:class "content-links"}
      (map personal-link links-personal-data/data)
    ]))

(defn book-link [datum]
  [:a 
    {
     :href (:href datum)
     :target "_blank"
     :title (:title datum)
    } 
    [:div
      [:img
        {
         :alt (:title datum)
         :src (:img datum)
        }
      ]
    ]
  ])

(def books-content
  (list
    [:div 
      {:class "content-title"}
      "books i love"]
    [:div 
      {:class "content-links"}
      (map book-link links-books-data/data)
    ]))

(def talk-link personal-link)

(def talks-content
  (list
    [:div 
      {:class "content-title"}
      "talks I've enjoyed"]
    [:div 
      {:class "talk-links content-links"}
      (map talk-link links-talks-data/data)
    ]))

(def links-content 
  (list
    [:div
      {:class "content-header"}
      [:span links-data/page-name]
      [:hr]
    ]
    [:br]
    personal-content
    books-content
    talks-content
  ))
    


(defn render []
  (render-full
    links-content))
