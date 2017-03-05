(ns wonderblog.style.mobile.posts
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.posts :as standard-posts]
    )
  )


(def style
  (eval standard-posts/style*))
