(ns wonderblog.html.content
  (:require
    [wonderblog.constants :as constants]
  )
  )


(defn content-container [& content]
	[:div {:class "content-container"}
		content
  ])

(defn html [inner-content]
  (content-container inner-content))

