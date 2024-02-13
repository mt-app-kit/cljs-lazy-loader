
(ns lazy-loader.api
    (:require [lazy-loader.side-effects :as side-effects]
              [lazy-loader.views :as views]
              [lazy-loader.env :as env]
              [lazy-loader.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; @--- Image sensor
; (defn my-image-sensor
;   []
;   [image-sensor :my-loader {:get-element-f (fn [_] (.getElementById js/document "my-element"))
;                             :uri "/my-image.png"}])
;
; @--- Image element
; (defn my-ui
;   []
;   [:<> [:div {:id "my-offset" :style {:height :100vh :width :100%}}]
;        [my-image-sensor]
;        [:div {:id "my-element" :style {:height :100px :width :100px}}]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (lazy-loader.env/*)
(def image-load-failed? env/image-load-failed?)
(def image-loaded?      env/image-loaded?)

; @redirect (lazy-loader.side-effects/*)
(def load-image! side-effects/load-image!)

; @redirect (lazy-loader.state/*)
(def URIS state/URIS)

; @redirect (lazy-loader.views/*)
(def image-sensor views/image-sensor)
