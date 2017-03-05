(ns wonderblog.util)


(defn- same-first-key? [h1, h2]
  (and
    (not (coll? (first h1)))
    (= (first h1) (first h2))))

(defn- get-next-content [h]
  (if
    (and
      (>= (count h) 3)
      (vector? (nth h 2)))
    (nth h 2)
    ;else
    nil))

(defn- get-adjacent-map [h]
  (if
    (and
      (>= (count h) 2)
      (map? (nth h 1)))
    (nth h 1)
    ;else
    {}))

(defn- merge-adjacent-maps [h1, h2]
  (merge
    (get-adjacent-map h1)
    (get-adjacent-map h2)))

(defn- get-key [h]
  (first h))

(defn hmerge [h1, h2] ; DEVNOTE - would like to replace this. this feels error prone.
  "Takes two hiccup-like vectors and merges them recursively."
  (cond
    (empty? h1)
      nil
    (same-first-key? h1 h2)
      [
        (get-key h1)
        (merge-adjacent-maps h1 h2)
        (hmerge
          (get-next-content h1)
          (get-next-content h2))
      ]
    :else
      h2))