(ns bdnc.pages.about.base
  (:require
   [bdnc.pages.about.svgs :as svgs]
   [goog.string :as gstring]
   [goog.string.format]))

(defn hello [props]
  [:div props
   svgs/hello-graphic])

(defn component* [id, content-all]
  (fn [props]
    [:div (conj props
                {:id id})
     [:div {:class ["absolute", "bottom-[39%]", "2xl:bottom-[34%]", "flex", "flex-col", "font-extrabold", "gap-[3vh]", "px-6", "2xl:px-[6rem]", "text-[1.1rem]", "portrait:md:text-[1.75rem]","lg:text-[1.75rem]", "portrait:lg:text-[2rem]", "2xl:text-[1.75rem]", "text-[#f9eac4]", "2xl:text-slate-600"]}
      (when (= :about id)
        [hello {:class ["2xl:fill-slate-600", "2xl:top-[2.5rem]", "2xl:w-[12rem]", "fill-[#f9eac4]", "h-auto", "md:top-[2rem]", "md:w-[8rem]", "relative", "top-[1.5rem]", "w-[5rem]"]}])
      (for [[content-id, content] content-all
            :let [text (:text content)
                  id (gstring/format "%s-content" (name content-id))]]
        [:p {:key id
             :id id}
         text])]]))
