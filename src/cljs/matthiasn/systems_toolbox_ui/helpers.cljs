(ns matthiasn.systems-toolbox-ui.helpers
  (:require [cljs.pprint :as pp]))

(defn by-id
  "Helper function, gets DOM element by ID."
  [id]
  (.getElementById js/document id))

(defn now
  "Get formatted timestamp string for time of call."
  []
  (.toISOString (js/Date.)))

(def request-animation-frame
  (or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      (.-mozRequestAnimationFrame js/window)
      (.-msRequestAnimationFrame js/window)
      (fn [callback] (js/setTimeout callback 17))))

(defn pp-div
  "Renders counter view which observes the state held by the state component. Clicking
  on it sends an increment command message."
  [current-state]
  [:pre [:code (with-out-str (pp/pprint current-state))]])
