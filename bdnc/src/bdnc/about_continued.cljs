(ns bdnc.about-continued
  (:require
   [goog.string :as gstring]
   [goog.string.format]))

(def what {:text "Here you'll find links to my resume as well as associated things such as my linkedin and github accounts. "})
(def thanks {:text "Thanks for checking me out - and if you're a fellow programmer or someone who's looking for one, I'd love to chat and talk about potentially working on something that's compelling and meaningful."})

(def content-all {:what what
                  :thanks thanks})

(defn component [props]
  [:div#about-continued
   props
   [:div {:class ["flex", "flex-col", "gap-8", "px-6", "text-lg", "text-[#f9eac4]", "font-extrabold"]}
    (for [[content-id, content] content-all
          :let [text (:text content)
                id (gstring/format "%s-content" (name content-id))]]
      [:p {:key id
           :id id}
       text])]])
