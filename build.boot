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
  '[wonderblog.tasks.core :refer [generate-html, generate-posts]]
  )


(deftask build
  []
  (comp
    (garden :pretty-print true :styles-var 'wonderblog.style.main/all-style :output-to "css/style.css")
    (cljs :optimizations :none :source-map true)
    ; main pages
    (generate-html :filename "index.html" :htmlfn 'wonderblog.html.about/render)
    (generate-html :filename "about.html" :htmlfn 'wonderblog.html.about/render)
    (generate-html :filename "links.html" :htmlfn 'wonderblog.html.links/render)
    (generate-html :filename "posts.html" :htmlfn 'wonderblog.html.posts/render)
    ; posts
    (generate-posts)
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
