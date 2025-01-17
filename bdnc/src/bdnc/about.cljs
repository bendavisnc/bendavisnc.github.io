(ns bdnc.about
  (:require
   [goog.string :as gstring]
   [goog.string.format]))

(def who  {:text "I'm a programmer with years of real world experience adding features and solving issues for backend systems built on various java based tech stacks."})
(def job {:text "I think of my job as managing systems that are naturally complex and doing my best to reign in whatever's currently verging on chaos. I do my best to make the ordinary stuff simple and the out-of-the-ordinary stuff manageable."})
(def joy {:text "I enjoy working as part of a small team and am happy when accountability and comradery make for real results being achieved. "})

(def content-all {:who who
                  :job job
                  :joy joy})

(defn component [props]
  [:div#about
   props
   [:div {:class ["flex", "flex-col", "gap-8", "px-6", "text-xl", "text-[#f9eac4]", "font-extrabold", "shadow-slate-950/50", "text-shadow-sm"]}
    (for [[content-id, content] content-all
          :let [text (:text content)
                id (gstring/format "%s-content" (name content-id))]]
      [:p {:key id
           :id id}
       text])]])
