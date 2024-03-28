
(ns lazy-loader.side-effects
    (:require [dom.api           :as dom]
              [fruits.css.api    :as css]
              [lazy-loader.env   :as env]
              [common-state.api :as common-state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn set-loaded-image!
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:get-element-f (function)(opt)
  ;  :uri (string)}
  ;
  ; @usage
  ; (set-loaded-image! :my-loader {...})
  [loader-id {:keys [get-element-f uri]}]
  (if-let [element (get-element-f loader-id)]
          (let [background-uri (css/url uri)
                tag-name       (dom/get-element-tag-name element)]
               (case tag-name "img" (dom/set-image-source!               element                               uri)
                                    (dom/set-element-inline-style-value! element "background-image" background-uri)))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn image-load-failed
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:on-error-f (function)(opt)
  ;  :uri (string)}
  ;
  ; @usage
  ; (image-load-failed :my-loader {...})
  [_ {:keys [on-error-f uri]}]
  (if uri        (common-state/assoc-state! :lazy-loader :uris uri :load-failed? true))
  (if on-error-f (on-error-f uri)))

(defn image-loaded
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:get-element-f (function)(opt)
  ;  :on-load-f (function)(opt)
  ;  :uri (string)}
  ;
  ; @usage
  ; (image-loaded :my-loader {...})
  [loader-id {:keys [get-element-f on-load-f uri] :as loader-props}]
  (if uri           (common-state/assoc-state! :lazy-loader :uris uri :loaded? true))
  (if on-load-f     (on-load-f uri))
  (if get-element-f (set-loaded-image! loader-id loader-props)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn load-image!
  ; @description
  ; 1. Downloads the image from the given URI.
  ; 2. Triggers the corresponding callback function.
  ; 3. If the 'get-element-f' is provided, and returns ...
  ;    ... the DOM Element object of an image tag, it sets the given URI as the image source of the element.
  ;    ... the DOM Element object of a non-image tag, it sets the given URI as the background image of the element.
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:get-element-f (function)(opt)
  ;  :on-error-f (function)(opt)
  ;  :on-load-f (function)(opt)
  ;  :uri (string)}
  ;
  ; @usage
  ; (load-image! :my-image {:get-element-f (fn [loader-id] ...)
  ;                         :on-error-f    (fn [uri] ...)
  ;                         :on-load-f     (fn [uri] ...)
  ;                         :uri           "/my-image.png"})
  [loader-id {:keys [uri] :as loader-props}]
  (letfn [(on-load-failed-f [_] (image-load-failed loader-id loader-props))
          (on-load-f        [_] (image-loaded      loader-id loader-props))]
         (cond (env/image-loaded?      loader-id loader-props) (image-loaded      loader-id loader-props)
               (env/image-load-failed? loader-id loader-props) (image-load-failed loader-id loader-props)
               :load-image (let [image (js/Image.)]
                                (-> image .-onerror (set! on-load-failed-f))
                                (-> image .-onload  (set! on-load-f))
                                (-> image .-src     (set! uri))))))
