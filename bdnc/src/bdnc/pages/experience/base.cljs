(ns bdnc.pages.experience.base
  (:require
   ["react" :as react]
   [bdnc.helpers]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as r :refer [atom]]))

(def expand-icon
  [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :aria-hidden "true", :data-slot "icon"}
        [:path {:style {:fill "black"}
                :d "M 22.065089,12 A 10.06511,10.06511 0 0 1 12.000051,22.065109 10.06511,10.06511 0 0 1 1.9349112,12 10.06511,10.06511 0 0 1 12.000051,1.9348907 10.06511,10.06511 0 0 1 22.065089,12 Z"}]
        [:path {:style {:fill "white"}
                :d "m 11.459799,7.4983244 h 1.080402 v 3.9614746 h 3.961474 v 1.080402 h -3.961474 v 3.961474 H 11.459799 V 12.540201 H 7.4983249 v -1.080402 h 3.9614741 z"}]])

(def unexpand-icon
  [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :aria-hidden "true", :data-slot "icon"}
        [:path {:style {:fill "black"}
                :d "M 22.065089,12 A 10.06511,10.06511 0 0 1 12.000051,22.065109 10.06511,10.06511 0 0 1 1.9349112,12 10.06511,10.06511 0 0 1 12.000051,1.9348907 10.06511,10.06511 0 0 1 22.065089,12 Z"}]
        [:path {:style {:fill "white"}
                :d "m 16.501675,11.459799 v 1.080402 c 0,0 -1.003584,0 -9.0033501,0 v -1.080402 c 0,0 1.0035834,0 9.0033501,0 z"}]])

(def previous-icon
  [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :aria-hidden "true", :data-slot "icon"}
        [:path {:style {:fill "none"}
                :d "M 17.651505,1.0288952 6.6817382,11.999931 17.651505,22.971107"}]])

(defn circles-icon [count, active-index]
  (let [box-dimension 24
        max-count 10
        _ (when (> count max-count)
            (throw (new js/Error (gstring/format "Unexpected count of circles to draw (%s)." count))))
        cy (/ box-dimension 2)
        margin-percent (/ 1.0 50)
        margin (* box-dimension margin-percent)
        r (/ (- box-dimension
               (* (dec max-count) margin))
             (* 2 max-count))
        style-active {:fill "#f9eac4"}
        style-default {:fill "white"
                       :opacity (/ 2 3)}]
    [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :aria-hidden "true", :data-slot "icon"}
     (for [i (range count)
           :let [is-active? (= active-index i)
                 cx (+ (* (+ (* 2 r)
                             margin)
                          i)
                       (+ r
                          (* 2 r (.ceil js/Math
                                        (/ (- max-count count)
                                           2)))))]]
       [:circle {:style (if is-active? style-active style-default)
                 :key i
                 :r r
                 :cx cx
                 :cy cy}])]))

(def transition-classes {:one-and-only []
                         :together []
                         :vanished ["scale-0"]})

(defn apply-transition! [container-ref, y-top, item-id-active]
  (let [elements (.-children (first (.getElementsByTagName (.-current container-ref)
                                                           "ul")))]
    ;; _ (assert (= 3 (.-length elements)))]
    (doseq [child elements]
      (let [child-active? (and (not (nil? item-id-active))
                               (= (.-id child)
                                  (name item-id-active)))]
        (if child-active?
          (let [delta-y (- (.-top (.getBoundingClientRect child))
                           @y-top)]
            (println (gstring/format "Setting `translateY` transform of `%s` child element `%s` (-%s)."
                                     (.-id (.-current container-ref))
                                     (.-id child)
                                     delta-y))
            (set! (.-transform (.-style child))
                  (gstring/format "translateY(-%spx)"
                                  delta-y)))
          ;; else
          (do (println (gstring/format "Unsetting `translateY` transform of `%s` child element `%s`."
                         (.-id (.-current container-ref))
                         (.-id child)))
              (set! (.-transform (.-style child))
                    "")))))))

(defn init-y-top! [yt, container-ref]
  (let [top-child (first (.-children (first (.getElementsByTagName (.-current container-ref)
                                                                   "ul"))))
        position-y (.-top (.getBoundingClientRect top-child))]
    (println (gstring/format "Initializing position reference for show/hide animation inside `%s` (%s)."
                             (.-id (.-current container-ref))
                             position-y))
    (reset! yt position-y)))

(defn init-details-scroll-observer!
  "Sets up an observer to dispatch an event when a detail item is scrolled into view."
  [container-ref, id, company]
  (println "Setting up details scroll observer!")
  (let [target-ancestor (.-current container-ref)
        scroll-parent (some-> target-ancestor
                              (.querySelector (str "#" (name id)))
                              (.querySelector ".details"))]
    (if (nil? scroll-parent)
      (println (gstring/format "Can't init details scroll observer for `%s`." id))
      (let [observer (new js/IntersectionObserver (fn [entries]
                                                    (doseq [entry entries]
                                                      (when (.-isIntersecting entry)
                                                        (let [target (.-target entry)
                                                              i (js/parseInt (.getAttribute target
                                                                                            "data-i"))]
                                                          (println (gstring/format "Requesting new active state, `%s` for experience item detail, `%s`." i company))
                                                          (rf/dispatch [:experience/item-detail-active-request company i]))))
                                                    (clj->js {:root scroll-parent
                                                              :threshold 0.5})))]
        (doseq [target (.-children scroll-parent)]
          (.observe observer target))))))

(defn expand-button [props is-active?]
  [:button props
   (if is-active? unexpand-icon expand-icon)])

(defn circles [props, count, company]
  (let [active-index (or @(rf/subscribe [:experience/item-detail-active company])
                         0)]
    [:div props
     [circles-icon count, active-index]]))

(defn previous-button [props]
  [:button props
   previous-icon])

(defn next-button [props]
  [:button (update-in props [:class] conj "transform rotate-180")
   previous-icon])

(defn main-section [props, is-active?, onclick, {:keys [name, title, logo]}]
  [:div.main-section props
   [:div {:class ["flex", "flex-col"]}
    [:div {:class ["portrait:md:text-[4rem]", "text-[#f9eac4]", "text-[2rem]"]}
     name]
    [:div {:class ["font-light", "portrait:md:text-[1.5rem]"]}
     title]
    [expand-button
     {:class ["w-[1rem]", "h-auto", "md:w-[1.5rem]"]
      :on-click onclick}
     is-active?]]
   [:div {:class ["flex", "flex-col", "justify-center"]}
    [:div {:class ["w-[4rem]", "h-auto", "portrait:md:w-[8rem]", "fill-slate-600"]}
     logo]]])

(defn details-button-click [direction, company, details]
  (let [event-key (cond (= direction :next)
                        :experience/item-detail-active-next
                        (= direction :previous)
                        :experience/item-detail-active-previous
                        :else
                        (throw (new js/Error (gstring/format "Unexpected direction `%s`." direction))))]
    (rf/dispatch [event-key company details])))

(defn apply-details-scroll-reaction! [container-ref, experience-item-id, company, active-index]
  (println (gstring/format "Setting up details scroll reaction for `%s` (%s)." active-index company))
  (let [target-ancestor (.-current container-ref)
        _ (assert (not (nil? target-ancestor))
                  "`container-ref` is empty.")
        target (aget (.-children (.querySelector (.querySelector target-ancestor
                                                                 (str "#" (name experience-item-id)))
                                                 ".details"))
                     active-index)]
    (println (gstring/format "Scrolling to detail item `%s`." (.-id target)))
    (.scrollIntoView target (clj->js {:behavior "smooth"
                                      :block "end"
                                      :inline "nearest"}))))

(defn details-section [props, parent-id, container-ref, company, details]
  [:f> (fn []
         (let [detail-item-id-active @(rf/subscribe [:experience/item-detail-active company])
               _ (react/useEffect (fn []
                                    (apply-details-scroll-reaction! container-ref
                                                                    parent-id
                                                                    company
                                                                    (or detail-item-id-active 0))
                                    js/undefined)
                                  (clj->js [detail-item-id-active]))]
              [:div.details-section props
               [previous-button {:class ["details-previous-button" "w-[1rem]", "h-auto", "stroke-white", "stroke-[0.25rem]"]
                                 :on-click (fn []
                                             (details-button-click :previous company details))}]
               [:ol.details {:id (str (name company) "-details")
                             :class ["flex", "mt-[4%]", "overflow-auto", "snap-mandatory", "snap-x", "w-[40rem]"]}
                (for [[i, detail] (map-indexed vector details)
                      :let [id (gstring/format "detail-item-%s-%i"
                                               (name company)
                                               i)]]
                  [:li {:key id
                        :id id
                        :class ["shrink-0", "snap-start", "w-[40rem]"]
                        :data-i i}
                   [:span {:class ["inline-block"]}
                    detail]])]
               [next-button {:class ["details-next-button" "w-[1rem]", "h-auto", "stroke-white", "stroke-[0.25rem]"]
                             :on-click (fn []
                                         (details-button-click :next company details))}]]))])

;; Contains everything under a single company experience, eg `comcast`.
(defn experience-item [props, id, company, item, container-ref, y-top]
  [:f> (fn []
         (let [_ (react/useEffect (fn []
                                    ;; (init-details-scroll-observer! container-ref, id, company)
                                    js/undefined))]

           [:f> (fn []
                  (let [item-id-active @(rf/subscribe [:experience/item-active])
                        is-active? (= company item-id-active)
                        details (:details item)
                        transition-class-key (cond (nil? item-id-active)
                                                   :together
                                                   (= item-id-active id)
                                                   :one-and-only
                                                   :else
                                                   :vanished)]
                    [:li (update-in props [:class] concat (transition-class-key transition-classes))
                     [main-section {:id id
                                    :class ["flex", "font-bold" "justify-between", "w-[20rem]", "portrait:md:w-[40rem]"]}
                                   is-active?
                                   (fn []
                                     (when (nil? @y-top)
                                       (init-y-top! y-top container-ref))
                                     (when (.-current container-ref)
                                       (apply-transition!
                                         container-ref
                                         y-top
                                         (when-not is-active? id)))
                                     (let [item-active (if is-active? nil company)]
                                       (println (gstring/format "Setting new active state, `%s` for experience item, `%s`." item-active company))
                                       (rf/dispatch [:experience/item-active item-active])))
                                   item]
                     [details-section {:id (str (name id) "-details")
                                       :class (concat ["flex", "font-bold", "overflow-scroll", "portrait:md:text-[1.5rem]", "text-[#f9eac4]", "w-dvw", "justify-around"]
                                                      ;; ["invisible", "h-0"])}
                                                      (if is-active?
                                                        ["h-48"]
                                                        ["invisible", "h-0"]))}
                                      id
                                      container-ref
                                      company
                                      details]
                     [circles {:id (str (name id)
                                        "-circles")
                               :class (concat ["w-[12rem]"]
                                              (if is-active?
                                                ["h-auto"]
                                                ["invisible", "h-0"]))}
                              (count details)
                              company]]))]))])

(defn experience-items [props, component-id, items, container-ref, y-top]
  [:ul props
   (for [[item-id, item] items
         :let [item-id-full (keyword (gstring/format "%s-%s" (name component-id)
                                                             (name item-id)))]]
     [experience-item {:key item-id-full
                       :id item-id-full
                       :class ["duration-1000", "flex", "flex-col", "items-center", "transition"]}
                      item-id-full
                      item-id
                      item
                      container-ref
                      y-top])])

(defn component* [id, content-all]
  (fn [props]
    (let [container-ref (.createRef react)
          y-top (atom nil)]
      [:div (conj props
                  {:id id
                   :ref container-ref})
       [experience-items {:class ["flex", "flex-col", "absolute", "bottom-[39%]", "pt-[20%]", "h-[50%]", "gap-[1.5rem]"]}
                         id
                         content-all
                         container-ref
                         y-top]])))
