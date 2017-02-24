(ns wonderblog.html.links
	(:require 
    [wonderblog.html.base :as base]
    [wonderblog.html.banner :as banner]
    [wonderblog.html.content :as content]
    [wonderblog.data.links :as links-data]
  )
  )

(def links-content 
	`(
		[:div
		  {:class "content-header"}
		  [:span ~links-data/page-name]
			[:hr]
    ]
		[:br]
		[:div
		  "ello mate replace me soon!"]))
 		


(defn render []
	(base/render 
		(banner/html)
		(content/html links-content)))
