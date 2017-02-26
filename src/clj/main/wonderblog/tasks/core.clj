(ns wonderblog.tasks.core
  (:require
    [boot.core :as boot]
    [boot.core :refer [deftask]]
    [clojure.java.io :as io]
    [wonderblog.html.core :as html]
    [wonderblog.core]
    [wonderblog.data.posts.entries :as posts-entries]
    [wonderblog.html.posts :as posts-html]
  ))

;;
;;
;; Tasks to generate our html content.

(deftask generate-html
  "Generate html files from hiccup render functions."
  [f filename FILENAME str "The filename to use."
   r htmlfn HTMLFN sym "The html function to invoke."]
  (fn [next-task]
    (fn [fileset]
      (let [
          tmp-d (boot/tmp-dir!)
          newfile (io/file tmp-d filename)
          fn-to-invoke (deref (resolve htmlfn))
        ]
        (do
          (use 'wonderblog.core :reload-all)
          (spit newfile (fn-to-invoke))
          (next-task 
            (boot/commit!
              (boot/add-resource fileset tmp-d))))))))


(deftask generate-posts []
  (fn [next-task]
    (fn [fileset]
      (let [
          tmp-d (boot/tmp-dir!)
        ]
        (do
          (use 'wonderblog.core :reload-all)
          (dorun
            (for [entry-data posts-entries/data]
              (let [
                  newfile (io/file tmp-d (:href entry-data))
                ]
                (do
                  (io/make-parents newfile)
                  (spit newfile (posts-html/render-post-page entry-data))))))
          (next-task 
            (boot/commit!
              (boot/add-resource fileset tmp-d))))))))


