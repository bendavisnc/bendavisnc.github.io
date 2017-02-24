(ns wonderblog.html.about
	(:require 
    [wonderblog.html.base :as base]
    [wonderblog.html.banner :as banner]
    [wonderblog.html.content :as content]
    [wonderblog.data.about :as about-data]
  )
  )

(def about-content 
	`(
		[:div
		  {:class "content-header"}
		  ~about-data/page-name
			[:hr]
    ]
		[:br]
		~@
	  (reduce 
			(fn [acc, d]
				(conj
					acc
					[:div 
					  {:class "content-title"}
					  (:title d)]
					[:div 
					  {:class "content-text"}
					  [:p
						  (:text d)
		        ]
		      ]
   				))
			[]
   		about-data/data)))
 		


(defn render []
	(base/render 
		(banner/html)
		(content/html about-content)))
