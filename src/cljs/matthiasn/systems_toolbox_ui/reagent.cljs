(ns matthiasn.systems-toolbox-ui.reagent
  (:require [reagent.core :as r :refer [create-class atom]]
            [matthiasn.systems-toolbox-ui.spec :as spec]
            [cljs.spec :as s]
            [matthiasn.systems-toolbox.spec :as st-spec]
            [matthiasn.systems-toolbox-ui.helpers :refer [by-id]]))

(defn init
  "Return clean initial component state atom."
  [reagent-cmp-map dom-id init-state init-fn put-fn]
  (let [initial-state (or init-state {})
        local (atom initial-state)
        observed (atom {})
        cmd (fn ([& r] (fn [e] (.stopPropagation e) (put-fn (vec r)))))
        reagent-cmp (create-class reagent-cmp-map)
        view-cmp-map {:observed observed
                      :local    local
                      :put-fn   put-fn
                      :cmd      cmd}]
    (r/render [reagent-cmp view-cmp-map] (by-id dom-id))
    (when init-fn (init-fn view-cmp-map))
    {:state    {:local local}
     :observed observed}))

(defn cmp-map
  "Creates a component map for a UI component using Reagent. This map can then be used by the comp/make-component
  function to initialize a component. Typically, this would be done by the switchboard."
  {:added "0.3.1"}
  [{:keys [cmp-id view-fn lifecycle-callbacks dom-id initial-state init-fn cfg handler-map state-pub-handler
           observed-xform] :as cmp-map}]
  (let [snapshot-wrapper (fn [m] (view-fn (merge m {:current-state @(:observed m)})))
        reagent-cmp-map (merge lifecycle-callbacks
                               {:reagent-render snapshot-wrapper})
        mk-state (partial init reagent-cmp-map dom-id initial-state init-fn)]
    (st-spec/valid-or-no-spec? :st-ui/cmp-map cmp-map)
    {:cmp-id            cmp-id
     :state-fn          mk-state
     :observed-xform    observed-xform
     :handler-map       handler-map
     :state-pub-handler state-pub-handler
     :opts              (merge cfg {:watch :local})}))
