(ns bdnc.pages.contact.contact
  (:require
   [bdnc.pages.contact.links :refer [links]]
   [goog.string :as gstring]
   [goog.string.format]))

(defn component [props]
  [:div#contact
   props
   [:ul {:class ["absolute", "bottom-[39%]", "2xl:bottom-[12%]", "flex", "flex-col", "gap-8", "md:gap-[5vh]", "pt-[8vh]"]}
    (for [[link-id, link] links
          :let [link-name (:name link)
                path (:path link)
                id (gstring/format "%s-link" (name link-id))
                logo (:logo link)]]
      [:li {:key id}
       [:a {:id id
            :href path}
        [:div {:class ["flex", "items-center", "text-5xl", "portrait:md:text-[5rem]", "md:text-[3rem]", "2xl:text-[8rem]", "text-[#f9eac4]", "2xl:text-[#3fc7b5]", "font-black", "gap-1.5"]}
         [:div {:class ["fill-slate-600", "w-12", "md:w-[5rem]", "2xl:w-[8rem]", "md:w-[3rem]", "portrait:md:w-[5rem]", "h-auto"]}
          logo]
         [:span {:class []}
          link-name]]]])]])
