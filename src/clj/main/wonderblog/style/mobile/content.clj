(ns wonderblog.style.mobile.content
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.content :as standard-content]
    )
  )


(def style
  (eval standard-content/style*))
