(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]
              [re-frame.core :as rf]))

(enable-console-print!)

;; // Target the element you want to observe
;; const target = document.querySelector('.target');

;; // Create an Intersection Observer
;; const observer = new IntersectionObserver((entries) => {
;;   entries.forEach(entry => {
;;     if (entry.isIntersecting) {
;;       console.log('The div is visible!');
;;       // Perform your action here
;;     } else {
;;       console.log('The div is not visible.');
;;     }
;;   });
;; }, {
;;   root: null, // Observe within the viewport
;;   threshold: 0.1 // Trigger when 10% of the element is visible
;; });

;; // Start observing the target
;; observer.observe(target);

(defn scroll-observe! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error "`scroll-observe!` target is null")))
        observer (new js/IntersectionObserver (fn [entries] 
                                                (.forEach entries (fn [entry]
                                                                    (if (.-isIntersecting entry) 
                                                                      (println (str target-selector " is visibile"))
                                                                      (println (str target-selector " is not visibile"))))))
                                           (clj->js {:root nil 
                                                     :threshold 0.1}))]
    (.observe observer target)))

(rf/reg-sub
  :title
  (fn [db _] 
    "neat title"))

(defn header []
  (let [title @(rf/subscribe [:title])]
    [:div#header {:class ["bg-white", "w-dvw min-h-24 fixed top-0 left-0"]}
      [:span#title title]]))

(defn page-a []
  [:div#a {:class ["bg-[#4686f2]", "h-dvh"]}])

(defn page-b []
  [:div#b {:class ["bg-[#fc05f6]", "h-dvh"]}])

(defn page-c []
  [:div#c {:class ["bg-[#fecd41]", "h-dvh"]}])


(defn root []
  [:div#root-container {:class "relative"}
    [:<> 
      [header]
      [page-a]
      [page-b]
      [page-c]]])

(defn init! []
  (reagent-dom/render [root]
                      (.getElementById js/document 
                                       "app"))
  (dorun (for [id ["#a", "#b", "#c"]]
           (scroll-observe! id))))

(defn on-js-reload [])

(init!)