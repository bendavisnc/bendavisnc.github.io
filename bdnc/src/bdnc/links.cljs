(ns bdnc.links)

(def a {:name "contact"
        :path "#contact"})

(def b {:name "b"
        :path "#b-page"})

(def c {:name "c"
        :path "#c-page"})

(def all [a, b, c])

(defn name [link]
  (:name link))

(defn path [link]
  (:path link))
