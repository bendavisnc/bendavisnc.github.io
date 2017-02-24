(ns wonderblog.html.posts
	(:require 
    [wonderblog.html.base :as base]
    [wonderblog.html.banner :as banner]
    [wonderblog.html.content :as content]
    [wonderblog.data.posts :as posts-data]
  )
  )

(def posts-content 
	`(
		[:div
		  {:class "content-header"}
		  [:span ~posts-data/page-name]
			[:hr]
    ]
		[:br]
		[:div
		  "todo soon"]))
 		


(defn render []
	(base/render 
		(banner/html)
		(content/html posts-content)))
