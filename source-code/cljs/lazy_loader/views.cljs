
(ns lazy-loader.views
    (:require [fruits.random.api         :as random]
              [intersection-observer.api :as intersection-observer]
              [lazy-loader.side-effects  :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn image-sensor
  ; @description
  ; Sets up an intersection observer that triggers the 'load-image!' function when the sensor intersects the viewport.
  ;
  ; @param (keyword)(opt) loader-id
  ; @param (map) loader-props
  ; {:get-element-f (function)(opt)
  ;  :on-error-f (function)(opt)
  ;  :on-load-f (function)(opt)
  ;  :uri (string)}
  ;
  ; @usage
  ; [image-sensor {...}]
  ;
  ; @usage
  ; [image-sensor :my-loader {...}]
  ;
  ; @usage
  ; [image-sensor :my-loader {:get-element-f (fn [loader-id] ...)
  ;                           :on-error-f    (fn [uri] ...)
  ;                           :on-load-f     (fn [uri] ...)
  ;                           :uri           "/my-image.png"}]
  ([loader-props]
   [image-sensor (random/generate-keyword) loader-props])

  ([loader-id loader-props]
   (letfn [(callback-f [intersect?] (if intersect? (side-effects/load-image! loader-id loader-props)))]
          [intersection-observer/sensor loader-id {:callback-f callback-f}])))
