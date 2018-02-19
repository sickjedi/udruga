import { BaseEntity } from './../../shared';

export const enum ReasonInactive {
    'REQUEST',
    'INACTIVE',
    'KICKED'
}

export class Member implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public surname?: string,
        public oib?: string,
        public dateofbirth?: any,
        public address?: string,
        public cellphone?: string,
        public email?: string,
        public datecreated?: any,
        public memberfrom?: any,
        public active?: boolean,
        public dateinactive?: any,
        public reasoninactive?: ReasonInactive,
        public lastupdated?: any,
    ) {
        this.active = false;
    }
}
