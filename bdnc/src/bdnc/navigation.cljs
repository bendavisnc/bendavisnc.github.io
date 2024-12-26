(ns bdnc.navigation
  (:require
   [bdnc.links :as links]
   [goog.string :as gstring]
   [goog.string.format]))

(defn component []
  [:div#navigation
   {:class ["h-dvh", "flex", "justify-center", "pt-32"]}
   [:ul {:class ["flex", "flex-col", "gap-8"]}
    (for [[link-id, link] links/all
          :let [link-name (:name link)
                path (:path link)
                id (gstring/format "%s-link" (name link-id))
                logo (:logo link)]]
      [:li {:key id}
       [:a {:id id
            :href path}
        [:div {:class ["flex", "items-center", "text-5xl", "text-[#f9eac4]", "font-black", "gap-1.5"]}
         [:div {:class ["fill-slate-600"]}
          logo]
         [:span {:class []}
          link-name]]]])]])
