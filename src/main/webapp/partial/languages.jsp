
<div class="dropdown ms-auto w-max" x-data="dropdown" @click.outside="open = false">
    <a
            href="javascript:;"
            class="flex items-center gap-2.5 rounded-lg border border-white-dark/30 bg-white px-2 py-1.5 text-white-dark hover:border-primary hover:text-primary dark:bg-black"
            :class="{'!border-primary !text-primary' : open}"
            @click="toggle"
    >
        <div>
            <img
                    :src="`assets/images/flags/${'${'}currentLang.toUpperCase()}.svg`"
                    alt="image"
                    class="h-5 w-5 rounded-full object-cover"
            />
        </div>
        <div x-text="currentLang" class="text-base font-bold uppercase"></div>
        <span class="shrink-0" :class="{'rotate-180' : open}">
                                            <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path
                                                        d="M6.99989 9.79988C6.59156 9.79988 6.18322 9.64238 5.87406 9.33321L2.07072 5.52988C1.90156 5.36071 1.90156 5.08071 2.07072 4.91154C2.23989 4.74238 2.51989 4.74238 2.68906 4.91154L6.49239 8.71488C6.77239 8.99488 7.22739 8.99488 7.50739 8.71488L11.3107 4.91154C11.4799 4.74238 11.7599 4.74238 11.9291 4.91154C12.0982 5.08071 12.0982 5.36071 11.9291 5.52988L8.12572 9.33321C7.81656 9.64238 7.40822 9.79988 6.99989 9.79988Z"
                                                        fill="currentColor"
                                                />
                                            </svg>
                                        </span>
    </a>
    <ul
            x-cloak
            x-show="open"
            x-transition
            x-transition.duration.300ms
            class="top-11 grid w-[280px] grid-cols-2 gap-y-2 !px-2 font-semibold text-dark ltr:-right-14 rtl:-left-14 dark:text-white-dark dark:text-white-light/90 sm:ltr:-right-2 sm:rtl:-left-2"
    >
        <template x-for="item in languages">
            <li>
                <a
                        href="javascript:;"
                        class="hover:text-primary"
                        @click="setLanguage(item.value),toggle()"
                        :class="{'bg-primary/10 text-primary' : currentLang == item.value}"
                >
                    <img
                            class="h-5 w-5 rounded-full object-cover"
                            :src="`assets/images/flags/${'${'}item.value.toUpperCase()}.svg`"
                            alt="image"
                    />
                    <span class="ltr:ml-3 rtl:mr-3" x-text="item.key"></span>
                </a>
            </li>
        </template>
    </ul>
</div>
