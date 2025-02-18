(ns bdnc.navigation
  (:require
   [bdnc.links :as links]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]))

(defn component [props]
  [:div#navigation
   props
   [:ul {:class ["absolute", "bottom-[56%]", "flex", "flex-col", "font-black" "justify-end" "landscape:lg:bottom-[26%]", "landscape:md:bottom-[20%]", "landscape:md:text-[3rem]", "landscape:lg:text-[6rem]", "portrait:md:bottom-[42%]", "portrait:md:text-[6rem]", "text-[#f9eac4]", "text-[3rem]"]}
    (for [page-id (rest (keys pages/all))
          ;; `rest` because skip navigation itself
          :when (not (:continued (page-id pages/all)))
          :let [link-id page-id
                link (link-id links/all)
                _ (when-not link
                    (.error js/console (gstring/format "No link for `%s`."
                                                       link-id)))
                link-name (:name link)
                path (:path link)
                id (gstring/format "%s-link" (name link-id))]]
      [:li {:key id}
       [:a {:id id
            :href path}
        [:div {:class ["flex", "items-center"]}
         [:span {:class []}
          link-name]]]])]])
