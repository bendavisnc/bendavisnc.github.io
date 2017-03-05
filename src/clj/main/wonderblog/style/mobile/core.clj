(ns wonderblog.style.mobile.core
  (:require
    [wonderblog.style.mobile.main :as main]
    [wonderblog.style.mobile.banner :as banner]
    [wonderblog.style.mobile.content :as content]
    [wonderblog.style.mobile.posts :as posts]
  ))

(def all-style
  [
    main/style
    banner/style
    content/style
    posts/style
  ])



