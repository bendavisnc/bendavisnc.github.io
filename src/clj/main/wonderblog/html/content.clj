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
	"Wraps our content in a standard container."
  (content-container inner-content))

