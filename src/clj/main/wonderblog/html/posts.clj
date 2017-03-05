(ns wonderblog.html.posts
  (:require 
    [wonderblog.data.posts.core :as posts-data]
    [wonderblog.data.posts.entries :as posts-entries]
    [wonderblog.html.core :refer [render-full]]
    [clojure.java.io :as io]
  )
  )

(def post-links
  (map
    (fn [d]
      [:a {:href (:href d)} (:title d)])
    posts-entries/data))


(def posts-content 
  (list
    [:div
      {:class "content-header"}
      [:span posts-data/page-name]
      [:hr]
    ]
    [:br]
    [:div
      {
        :class "posts-content"
      }
      post-links]))

      
    


(defn render []
  "Render the page that has the posts links."
  (render-full 
    posts-content))

(defn single-post-content [post-title, post-text]
  (list
    [:div
      {:class "content-header posts-header"}
      [:span post-title]
      [:hr]
    ]
    [:br]
    [:div {:class "content-text"}
      [:p post-text]
    ]
  ))

(defn render-post-page [post-datum]
  "Render a page that houses a post."
  (render-full 
    (single-post-content 
      (:title post-datum)
      (slurp
        (io/resource (:src post-datum))))))
