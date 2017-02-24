(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[
                  [hiccup "1.0.5"]
                  [org.martinklepsch/boot-garden "1.3.2-0"]
                  [pandeiro/boot-http "0.6.3-SNAPSHOT"]])


(require 
  '[org.martinklepsch.boot-garden :refer [garden]]
  '[pandeiro.boot-http :refer [serve]]
  '[boot.core :as boot]
  '[clojure.java.io :refer [file]]
  '[wonderblog.html.core :as html]
  )

(deftask generate-html
  "Generate html files from hiccup render functions."
  []
  (fn [next-task]
    (fn [fileset]
      (let [
          tmp-d (boot/tmp-dir!)
          newfile (file tmp-d "index.html")
        ]
        (do
          (use 'wonderblog.html.core :reload-all)
          (spit newfile (html/render))
          (next-task 
            (boot/commit!
              (boot/add-resource fileset tmp-d))))))))

(deftask build
  []
  (comp
    (garden :pretty-print true :styles-var 'wonderblog.style.main/all-style :output-to "css/style.css")
    (generate-html)
    (target)
    (notify)
    ))

(deftask dev
  []
  (comp 
    (watch)
    (build)
    ; (serve :resource-root "public" :port 8082)
    ))
