(ns wonderblog.style.mobile.main
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.main :as standard-main]
    )
  )


(def style
  (eval standard-main/style*))
