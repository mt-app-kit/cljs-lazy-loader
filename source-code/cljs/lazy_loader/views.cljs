
(ns lazy-loader.views
    (:require [intersection-observer.api :as intersection-observer]
              [lazy-loader.side-effects :as side-effects]
              [fruits.random.api :as random]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn image-sensor
  ; @description
  ; Sets up an intersection observer that triggers the 'load-image!' function.
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
