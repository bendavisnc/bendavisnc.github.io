(ns wonderblog.style.standard.core
  (:require
    [wonderblog.style.standard.main :as main]
    [wonderblog.style.standard.constants :as constants]
    [wonderblog.style.standard.banner :as banner]
    [wonderblog.style.standard.content :as content]
    [wonderblog.style.standard.posts :as posts]
  ))

(def all-style
  [
    main/style
    banner/style
    content/style
    posts/style
  ])



