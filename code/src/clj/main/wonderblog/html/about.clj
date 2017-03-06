(ns wonderblog.html.about
	(:require 
    [wonderblog.data.about :as about-data]
    [wonderblog.html.core :refer [render-full]]
  )
  )

(defn about-item [datum]
	(list
		[:div 
		  {:class "content-title"}
		  (:title datum)]
		[:div 
		  {:class "content-text"}
		  [:p
			  (:text datum)
	    ]
	  ]))

(def about-content 
	(list
		[:div
		  {:class "content-header"}
		  [:span about-data/page-name]
			[:hr]
    ]
		[:br]
		(mapcat about-item about-data/data)))



(defn render []
	(render-full about-content))
