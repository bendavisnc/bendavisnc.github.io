(ns wonderblog.html.core
  (:require
    [wonderblog.html.base :as base]
    [wonderblog.html.banner :as banner]
    [wonderblog.html.content :as content]
  ))

(defn render-full [inner-content]
	"Renders an entire page given inner content to display."
  (base/render 
    (banner/html)
    (content/html inner-content)))
 
