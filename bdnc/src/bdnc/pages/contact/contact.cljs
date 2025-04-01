(ns bdnc.pages.contact.contact
  (:require
   [bdnc.pages.contact.links :refer [links]]
   [goog.string :as gstring]
   [goog.string.format]))

(defn component [props]
  [:div#contact
   props
   [:ul {:class ["absolute", "bottom-[39%]", "2xl:bottom-[12%]", "flex", "flex-col", "gap-8", "md:gap-[5vh]", "md:gap-[7vh]", "pt-[8vh]"]}
    (for [[link-id, link] links
          :let [link-name (:name link)
                path (:path link)
                id (gstring/format "%s-link" (name link-id))
                logo (:logo link)]]
      [:li {:key id}
       [:a {:id id
            :href path}
        [:div {:class ["2xl:text-[#3fc7b5]", "2xl:text-[5rem]", "flex", "font-black", "gap-2", "items-center", "md:text-[3rem]", "portrait:md:text-[5rem]", "text-5xl", "text-[#f9eac4]"]}
         [:div {:class ["fill-slate-600", "w-12", "md:w-[5rem]", "2xl:w-[5rem]", "md:w-[3rem]", "portrait:md:w-[5rem]", "h-auto"]}
          logo]
         [:span {:class []}
          link-name]]]])]])
