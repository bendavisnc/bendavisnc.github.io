(ns bdnc.helpers)

(extend-protocol ICollection
  cljs.core.Keyword
  (-conj [this, other]
    (keyword (name this)
             (name other))))
