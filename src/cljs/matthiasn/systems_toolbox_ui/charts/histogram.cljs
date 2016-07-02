(ns matthiasn.systems-toolbox-ui.charts.histogram
  (:require [matthiasn.systems-toolbox-ui.charts.math :as m]))

(def text-default {:stroke "none" :fill "black" :style {:font-size 12}})
(def text-bold (merge text-default {:style {:font-weight :bold :font-size 12}}))
(def x-axis-label (merge text-default {:text-anchor :middle}))
(def y-axis-label (merge text-default {:text-anchor :end}))
(def path-defaults {:fill :black :stroke :black :stroke-width 1})

(defn histogram-y-axis
  "Draws y-axis of a chart."
  [x y h mx]
  (let [incr (m/default-increment-fn mx)
        rng (range 0 (inc (m/round-up mx incr)) incr)
        scale (/ h (dec (count rng)))]
    [:g
     [:path (merge path-defaults {:d (str "M" x " " y "l 0 " (* h -1) " z")})]
     (for [n rng]
       ^{:key (str "yt" n)}
       [:path (merge path-defaults {:d (str "M" x " " (- y (* (/ n incr) scale)) "l -" 6 " 0")})])
     (for [n rng]
       ^{:key (str "yl" n)}
       [:text (merge y-axis-label {:x (- x 10) :y (- y (* (/ n incr) scale) -4)}) n])]))

(defn histogram-x-axis
  "Draws x-axis for histrogram."
  [x y mn mx w scale increment]
  (let [rng (range mn (inc mx) increment)]
    [:g
     [:path (merge path-defaults {:d (str "M" x " " y "l" w " 0 z")})]
     (for [n rng]
       ^{:key (str "xt" n)}
       [:path (merge path-defaults {:d (str "M" (+ x (* (- n mn) scale)) " " y "l 0 " 6)})])
     (for [n rng]
       ^{:key (str "xl" n)}
       [:text (merge x-axis-label {:x (+ x (* (- n mn) scale)) :y (+ y 20)}) n])]))

(defn histogram-view-fn
  "Renders a histogram."
  [{:keys [data x y w h x-label color bin-cf max-bins increment-fn]}]
  (let [mx (apply max data)
        mn (apply min data)
        rng (- mx mn)
        increment-fn (or increment-fn m/default-increment-fn)
        increment (increment-fn rng)
        mx2 (m/round-up (or mx 10) increment)
        mn2 (m/round-down (or mn 0) increment)
        x-scale (/ w (- mx2 mn2))
        bin-size (max (/ rng max-bins) (* (m/freedman-diaconis-rule data) bin-cf))
        binned-freq (frequencies (map (fn [n] (Math/floor (/ (- n mn) bin-size))) data))
        binned-freq-mx (apply max (map (fn [[_ f]] f) binned-freq))
        bins (inc (apply max (map (fn [[v _]] v) binned-freq)))
        bar-width (/ (* rng x-scale) bins)
        y-scale (/ (- h 20) binned-freq-mx)]
    [:g
     (if (> bins 4)
       (for [[v f] binned-freq]
         ^{:key (str "bf" x "-" y "-" v "-" f)}
         [:rect {:x      (+ x (* (- mn mn2) x-scale) (* v bar-width))
                 :y      (- y (* f y-scale))
                 :fill   color :stroke "black"
                 :width  bar-width
                 :height (* f y-scale)}])
       [:text {:x           (+ x (/ w 2))
               :y           (- y 50)
               :stroke      "none"
               :fill        "#DDD"
               :text-anchor :middle
               :style       {:font-weight :bold :font-size 24}}
        "insufficient data"])
     (histogram-x-axis x (+ y 7) mn2 mx2 w x-scale increment)
     [:text (merge x-axis-label text-bold {:x           (+ x (/ w 2))
                                           :y           (+ y 48)
                                           :text-anchor :middle})
      x-label]
     [:text (let [x-coord (- x 45)
                  y-coord (- y (/ h 3))
                  rotate (str "rotate(270 " x-coord " " y-coord ")")]
              (merge x-axis-label text-bold {:x         x-coord
                                             :y         y-coord
                                             :transform rotate}))
      "Frequencies"]
     (histogram-y-axis (- x 7) y h (or binned-freq-mx 5))]))

(defn histogram-view
  "Renders an individual histogram for the given data, dimension, label and color,
  with a reasonable size inside a viewBox, which will then scale smoothly into any
  div you put it in."
  [data label color]
  [:svg {:width   "100%"
         :viewBox "0 0 400 250"}
   (histogram-view-fn {:data     data
                       :x        80
                       :y        180
                       :w        300
                       :h        160
                       :x-label  label
                       :color    color
                       :bin-cf   0.8
                       :max-bins 25})])
