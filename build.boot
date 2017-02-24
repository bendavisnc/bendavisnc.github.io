(set-env!
 :source-paths #{"src/clj/main", "src/cljs/main"}
 :resource-paths #{"resources"}
 :dependencies '[
                  [hiccup "1.0.5"],
                  [org.martinklepsch/boot-garden "1.3.2-0"],
                  [adzerk/boot-cljs "2.0.0-SNAPSHOT"],
                  [pandeiro/boot-http "0.6.3-SNAPSHOT"]])


(require 
  '[org.martinklepsch.boot-garden :refer [garden]]
  '[pandeiro.boot-http :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]]
  '[boot.core :as boot]
  '[clojure.java.io :refer [file]]
  '[wonderblog.html.core :as html]
  )

(deftask generate-html
  "Generate html files from hiccup render functions."
  [f filename FILENAME str "The filename to use."
   r htmlfn HTMLFN sym "The html function to invoke."]
  (fn [next-task]
    (fn [fileset]
      (let [
          tmp-d (boot/tmp-dir!)
          newfile (file tmp-d filename)
          fn-to-invoke (deref (resolve htmlfn))
        ]
        (do
          (use 'wonderblog.html.core :reload-all)
          (spit newfile (fn-to-invoke))
          (next-task 
            (boot/commit!
              (boot/add-resource fileset tmp-d))))))))

(deftask build
  []
  (comp
    (garden :pretty-print true :styles-var 'wonderblog.style.main/all-style :output-to "css/style.css")
    (cljs :optimizations :none :source-map true)
    (generate-html :filename "index.html" :htmlfn 'wonderblog.html.about/render)
    (generate-html :filename "about.html" :htmlfn 'wonderblog.html.about/render)
    (generate-html :filename "links.html" :htmlfn 'wonderblog.html.links/render)
    (generate-html :filename "posts.html" :htmlfn 'wonderblog.html.posts/render)
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
