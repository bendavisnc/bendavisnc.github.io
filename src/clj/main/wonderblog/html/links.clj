(ns wonderblog.html.links
  (:require 
    [wonderblog.data.links.core :as links-data]
    [wonderblog.data.links.personal :as links-personal-data]
    [wonderblog.data.links.books :as links-books-data]
    [wonderblog.data.links.talks :as links-talks-data]
    [wonderblog.data.links.blogs :as links-blogs-data]
    [wonderblog.data.links.tech :as links-tech-data]
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
     :class "book-link"
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

(def blog-link personal-link)

(def blogs-content
  (list
    [:div 
      {:class "content-title"}
      "blogs that are way better than mine"]
    [:div 
      {:class "talk-links content-links"}
      (map blog-link links-blogs-data/data)
    ]))

(defn tech-link [datum]
  [:a 
    {
     :href (:href datum)
     :target "_blank"
     :title (:desc datum)
     :class "tech-link"
    } 
    [:div
      [:img
        {
         :alt (:title datum)
         :src (:img datum)
        }
      ]
      [:span
        (:title datum)
      ]
    ]
  ])

(def tech-content
  (list
    [:div 
      {:class "content-title"}
      "tech i'm into"]
    [:div 
      {:class "tech-links content-links"}
      (map tech-link links-tech-data/data)
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
    tech-content
    books-content
    talks-content
    blogs-content
  ))
    


(defn render []
  (render-full
    links-content))
